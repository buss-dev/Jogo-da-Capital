package br.buss.jogodacapital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<BrazilianState> states = new ArrayList<>();
    BrazilianState selectedState;
    ImageView imageView;
    TextView stateName, isCorrectAnswer;
    EditText editTextAnswer;
    ProgressBar progressBar;
    Button nextButton, answerButton;
    int answerCount, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.bandeirabrasil);

        stateName = findViewById(R.id.stateName);
        stateName.setText("Clique em próximo para iniciar!");

        editTextAnswer = findViewById(R.id.editTextAnswer);
        editTextAnswer.setEnabled(false);
        editTextAnswer.setHint("");

        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(answerCount);

        nextButton = findViewById(R.id.nextButton);
        answerButton = findViewById(R.id.answerButton);
        answerButton.setEnabled(false);

        isCorrectAnswer = findViewById(R.id.isCorrectAnswer);

        createStates();
    }

    public void validateAnswer(View view) {
        if (editTextAnswer.length() == 0) {
            Toast.makeText(this, "Digite uma resposta!", Toast.LENGTH_SHORT).show();
        } else {
            if (answerCount != 0) {
                String answer = editTextAnswer.getText().toString().trim();
                if (answer.equalsIgnoreCase(selectedState.getCapitalName())) {
                    score += 10;
                    isCorrectAnswer.setText("Parabéns, você acertou!");
                } else {
                    isCorrectAnswer.setText("Você errou!");
                }
            }
            answerButton.setEnabled(false);
            nextButton.setEnabled(true);
            TextView scoreView = findViewById(R.id.scoreView);
            scoreView.setText("Pontuação: " + score);
        }
    }

    public void getRandomState(View view) {
        editTextAnswer.setEnabled(true);
        editTextAnswer.setText("");
        editTextAnswer.setHint("Digite seu palpite...");

        nextButton.setEnabled(false);

        isCorrectAnswer.setText("");

        if (answerCount < 5) {
            answerButton.setEnabled(true);
            int randomIndex = new Random().nextInt(states.size());
            selectedState = states.get(randomIndex);
            states.remove(randomIndex);
            imageView.setImageResource(selectedState.getCapitalImage());
            stateName.setText(selectedState.getStateName());
            answerCount++;
            progressBar.setProgress(answerCount);
        } else if (answerCount == 5) {
            imageView.setImageResource(R.drawable.fim);
            stateName.setText("FIM");
            editTextAnswer.setEnabled(false);
            editTextAnswer.setText("Fim de Jogo...");
        } else {
            nextButton.setEnabled(false);
        }
    }

    public void createStates() {
        states.add(new BrazilianState("Paraná", "Curitiba", R.drawable.curitiba));
        states.add(new BrazilianState("Pernambuco", "Recife", R.drawable.recife));
        states.add(new BrazilianState("Piauí", "Teresina", R.drawable.teresina));
        states.add(new BrazilianState("Rio de Janeiro", "Rio de Janeiro", R.drawable.rio_de_janeiro));
        states.add(new BrazilianState("Rio Grande do Norte", "Natal", R.drawable.natal));
        states.add(new BrazilianState("Rio Grande do Sul", "Porto Alegre", R.drawable.porto_alegre));
        states.add(new BrazilianState("Rondônia", "Porto Velho", R.drawable.porto_velho));
        states.add(new BrazilianState("Roraima", "Boa Vista", R.drawable.boa_vista));
        states.add(new BrazilianState("Sergipe", "Aracaju", R.drawable.aracaju));
        states.add(new BrazilianState("Tocantins", "Palmas", R.drawable.palmas));
        states.add(new BrazilianState("Mato Grosso do Sul", "Campo Grande", R.drawable.campo_grande));
        states.add(new BrazilianState("Ceará", "Fortaleza", R.drawable.fortaleza));
        states.add(new BrazilianState("Bahia", "Salvador", R.drawable.salvador));
        states.add(new BrazilianState("Amazonas", "Manaus", R.drawable.manaus));
        states.add(new BrazilianState("Minas Gerais", "Belo Horizonte", R.drawable.belo_horizonte));
    }

    public class BrazilianState {
        String stateName;
        String capitalName;
        int capitalImage;

        public BrazilianState(String stateName, String capitalName, int capitalImage) {
            this.stateName = stateName;
            this.capitalName = capitalName;
            this.capitalImage = capitalImage;
        }

        public String getCapitalName() {
            return this.capitalName;
        }

        public String getStateName() {
            return this.stateName;
        }

        public int getCapitalImage() {
            return this.capitalImage;
        }

    }
}