package android.twitter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TweetListActivity extends Activity {

	private ListView listViewPostados;
	private TextView naoHaTweets;

	public static final int NOVO_TWEET = 0;
	public static final int VER_TWEET = 1;
	public static final int DELETAR_TWEET = 2;
	public static final int VOLTAR = 3;
	public static final String NOVO_TWEET_TEXT = "ntt";
	protected static final String VER_TWEET_TEXTO = "vtt";
	protected static final String POSICAO_TWEET_TEXTO = "ptt";

	// LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
	ArrayList<String> listItems = new ArrayList<String>();

	// DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
	ArrayAdapter<String> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.tweetlist);

		listViewPostados = (ListView) findViewById(R.id.listViewPostados);
		naoHaTweets = (TextView) findViewById(R.id.textViewNaoHaTweet);
		if (listItems.isEmpty()) {
			naoHaTweets.setVisibility(View.VISIBLE);
		} else {
			naoHaTweets.setVisibility(View.INVISIBLE);
		}

		listViewPostados.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(parent.getContext(),
						SeeTweetActivity.class);
				intent.putExtra(VER_TWEET_TEXTO, ((TextView) view).getText());
				intent.putExtra(POSICAO_TWEET_TEXTO, position);
				startActivityForResult(intent, VER_TWEET);
			}
		});

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listItems);

		listViewPostados.setAdapter(adapter);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case TweetListActivity.NOVO_TWEET:
			if (resultCode == NOVO_TWEET) {
				String novoTweet = data.getStringExtra(NOVO_TWEET_TEXT);
				if (novoTweet != null && novoTweet.length() > 0) {
					listItems.add(novoTweet);
					adapter.notifyDataSetChanged();
				}
			}
			break;
		case TweetListActivity.VER_TWEET:
			if (resultCode == DELETAR_TWEET) {
				int pos = data.getIntExtra(POSICAO_TWEET_TEXTO, -1);
				if (pos != -1) {
					listItems.remove(pos);
					adapter.notifyDataSetChanged();
				}
				break;
			}
			break;
		}
		if (listItems.isEmpty()) {
			naoHaTweets.setVisibility(View.VISIBLE);
		} else {
			naoHaTweets.setVisibility(View.INVISIBLE);
		}
	}

	public void novoTweet(View v) {
		Intent intent = new Intent(this, NewTweetActivity.class);
		startActivityForResult(intent, NOVO_TWEET);
	}
}