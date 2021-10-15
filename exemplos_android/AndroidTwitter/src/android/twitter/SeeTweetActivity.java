package android.twitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SeeTweetActivity extends Activity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.visualizartweet);
		Intent intent = getIntent();
		String text = intent.getStringExtra(TweetListActivity.VER_TWEET_TEXTO);
		textView = (TextView) findViewById(R.id.textViewTweet);
		textView.setText(text);
		
	}

	public void deleteItem(View v) {
		setResult(TweetListActivity.DELETAR_TWEET, getIntent());
		finish();
	}

	public void fazerNada(View v) {
		setResult(TweetListActivity.VER_TWEET);
		finish();
	}

}
