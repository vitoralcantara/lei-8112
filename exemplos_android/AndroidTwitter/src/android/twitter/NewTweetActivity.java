package android.twitter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewTweetActivity extends Activity {

	private EditText editTextPostar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.novotweet);

		editTextPostar = (EditText) findViewById(R.id.editTextPostar);
	}

	public void addItems(View v) {
		Uri uri = Uri.parse("");
		Intent result = new Intent(null, uri);
		result.putExtra(TweetListActivity.NOVO_TWEET_TEXT, editTextPostar
				.getText().toString());
		setResult(TweetListActivity.NOVO_TWEET, result);
		finish();
	}

	public void voltar(View v) {
		setResult(TweetListActivity.VOLTAR);
		finish();
	}

}
