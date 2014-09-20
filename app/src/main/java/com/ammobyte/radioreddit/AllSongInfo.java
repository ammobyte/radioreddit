/*
 * This file is part of radio reddit for Android.
 *
 * radio reddit for Android is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * radio reddit for Android is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with radio reddit for Android.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.ammobyte.radioreddit;

import android.os.Parcel;
import android.os.Parcelable;

public class AllSongInfo implements Parcelable {
    // Set by GetSongInfo
    public String reddit_id;
    public boolean upvoted;
    public boolean downvoted;
    public int votes;
    public boolean saved;
    
    // Set by GetStationStatus
    public String title;
    public String artist;
    public String redditor;
    public String genre;
    public String playlist;
    public String reddit_url;

    // Empty constructor needed to implement Parcelable
    public AllSongInfo() {
    }
    
    // Populates new object from parcel
    private AllSongInfo(Parcel source) {
        reddit_id = source.readString();
        final byte upvoted_byte = source.readByte();
        if (upvoted_byte == (byte) 1) {
            upvoted = true;
        } else {
            upvoted = false;
        }
        final byte downvoted_byte = source.readByte();
        if (downvoted_byte == (byte) 1) {
            downvoted = true;
        } else {
            downvoted = false;
        }
        votes = source.readInt();
        byte saved_byte = source.readByte();
        if (saved_byte == (byte) 1) {
            saved = true;
        } else {
            saved = false;
        }
        title = source.readString();
        artist = source.readString();
        redditor = source.readString();
        genre = source.readString();
        playlist = source.readString();
        reddit_url = source.readString();
    }
    
    public boolean equals(AllSongInfo song) {
        if (this.title.equals(song.title) 
                && this.artist.equals(song.artist)
                && this.genre.equals(song.genre)
                && this.redditor.equals(song.redditor)
                && this.playlist.equals(song.playlist)
                && this.reddit_url.equals(song.reddit_url)
                && this.upvoted == song.upvoted
                && this.downvoted == song.downvoted
                && this.votes == song.votes
                && this.saved == song.saved) {
            return true;
        }
        return false;
    }
    
    // CREATOR is a reserved object from Parcelable that inflates the object from a parcel source
    public static final Creator<AllSongInfo> CREATOR = new Creator<AllSongInfo>() {
        @Override
        public AllSongInfo createFromParcel(Parcel source) {
            return new AllSongInfo(source);
        }
        
        @Override
        public AllSongInfo[] newArray(int size) {
            return new AllSongInfo[size];
        }
    };
    
    @Override
    public int describeContents() {
        // Only primitives are used so we don't need anything here.
        return 0;
    }
    
    // Collapse object to parcel for transit
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reddit_id);
        // Parcel doesn't have a writeBoolean so we use a byte instead
        if (upvoted) {
            dest.writeByte((byte) 1);
        } else {
            dest.writeByte((byte) 0);
        }
        if (downvoted) {
            dest.writeByte((byte) 1);
        } else {
            dest.writeByte((byte) 0);
        }
        dest.writeInt(votes);
        if (saved) {
            dest.writeByte((byte) 1);
        } else {
            dest.writeByte((byte) 0);
        }
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(redditor);
        dest.writeString(genre);
        dest.writeString(playlist);
        dest.writeString(reddit_url);
    }
}

