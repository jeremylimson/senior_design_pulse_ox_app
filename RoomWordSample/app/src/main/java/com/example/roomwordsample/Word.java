package com.example.roomwordsample;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// represents SQLite table that will instantiate objects from rows

/*
 Each @Entity class represents a SQLite table. Annotate your class declaration to indicate that it's an entity.
 You can specify the name of the table if you want it to be different from the name of the class.
 This names the table "word_table".
 */

@Entity(tableName = "word_table")
public class Word {

    // Every entity needs a primary key. To keep things simple, each word acts as its own primary key.
    @PrimaryKey
    // Denotes that a parameter, field, or method return value can never be null.
    @NonNull
    // Specify the name of the column in the table if you want it to be different from the name of the member variable.
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String word) {this.mWord = word;}

    public String getWord(){return this.mWord;}
}
