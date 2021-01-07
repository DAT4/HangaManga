package com.example.hangamanga.ui.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hangamanga.R
import com.example.hangamanga.databinding.FragmentPlayGameBinding
import com.example.hangamanga.models.Game
import com.example.hangamanga.models.HighScore
import com.example.hangamanga.models.Letter

class PlayGameFragment : Fragment() {
    private lateinit var _binding: FragmentPlayGameBinding
    private val binding get() = _binding

    private val args: PlayGameFragmentArgs by navArgs()

    private lateinit var game: Game
    private lateinit var player: String
    private lateinit var timer: CountDownTimer
    private var time = 0
    private var hintCounter = 1
    private val letters = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val word = args.word
        word.make()
        game = Game(word)
        player = args.player
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createWord()
        binding.state.setImageResource(game.hangman.getState())
        generateKeyboard()

        timer = object : CountDownTimer(15 * 1000 , 1000) {
            override fun onTick(p0: Long) {
                time = (p0 / 1000).toInt()
                val minutes = p0 / 1000 / 60
                val seconds = p0 / 1000 % 60
                if (seconds > 9) {
                    binding.gameTime.text = "0$minutes:$seconds"
                } else {
                    binding.gameTime.text = "0$minutes:0$seconds"
                }
            }

            override fun onFinish() {
                endGame(false)
            }
        }.start()

        binding.gameHint.setOnClickListener {
            val des = AlertDialog.Builder(requireContext())
            des.setTitle("HINT $this.hintCounter")
            if (this.hintCounter == 1)
                des.setMessage(game.word.hint1)
            if (this.hintCounter == 2)
                des.setMessage(game.word.hint2)
            if (this.hintCounter == 3)
                des.setMessage(game.word.hint3)
            des.setPositiveButton("OK") { a, b -> }
            des.show()
            hintCounter++
        }
    }

    private fun createWord() {
        for (letter in game.word.letters) {
            val x = TextView(requireContext())
            x.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            x.layoutParams.width = 75
            x.textSize = 20F
            if (!letter.isGuessed()) {
                x.text = "_"
            }
            x.isActivated = false
            letters.add(x)
            binding.bogstaver.addView(x)
        }
    }

    private fun updateWord() {
        game.word.letters.forEachIndexed { i, letter ->
            if (letter.isGuessed()) {
                letters[i].text = game.word.letters[i].toString()
            }
        }
    }

    private fun generateKeyboard() {
        for (option in game.options) {
            val button = createButton(option.toString()) { guess(it, option) }
            binding.buttons.addView(button)
        }
    }

    private fun guess(button: Button, letter: Letter) {
        if (button.isActivated) {
            val stillAlive = game.guess(letter)
            if (!stillAlive) {
                endGame(false)
            } else {
                button.text = ""
                button.isActivated = false
                updateWord()
                if (game.isDone()) {
                    endGame(true)
                } else {
                    binding.state.setImageResource(game.hangman.getState())
                }
            }
        } else {
            Toast.makeText(requireContext(), "Its not a choice!", Toast.LENGTH_SHORT).show()
        }
    }

    private val buttonClick = AlphaAnimation(1F, 0.8F)

    private fun createButton(title: String, function: (button: Button) -> Unit): Button {
        val button =
            layoutInflater.inflate(R.layout.button_letter, null, false) as Button
        button.text = title
        button.isActivated = true
        button.setOnClickListener {
            button.startAnimation(buttonClick)
            function(button)
        }
        return button
    }

    private fun endGame(won: Boolean) {
        timer.cancel()
        val score =
            HighScore(
                player = player,
                time = this.time,
                hints = this.hintCounter,
                wrongs = this.game.hangman.level,
                word = this.game.word
            )
        val action =
            PlayGameFragmentDirections
            .actionPlayGameFragmentToEndGameFragment(score, won)

        findNavController().navigate(action)
    }
}