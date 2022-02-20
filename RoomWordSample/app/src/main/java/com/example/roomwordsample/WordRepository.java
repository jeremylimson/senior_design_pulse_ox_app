package com.example.roomwordsample;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

// implements the logic for deciding whether to fetch data from a network or use results cached in a local database.
class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    // in order to unit test the WordRepository, you have to remove the Application dependency
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    // room executes all queries on a separate thread
    // observed LiveData will notify the observer when the data has changed
    LiveData<List<Word>> getAllWords() {
        // returns LiveData list of words from Room
        return mAllWords;
    }

    // call this on a non-UI thread (not Main) or your app will throw an exception
    // Room ensures that there are no long running operations on the main thread blocking the UI
    void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }
}
