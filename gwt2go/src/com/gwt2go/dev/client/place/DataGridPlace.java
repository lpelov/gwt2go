package com.gwt2go.dev.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DataGridPlace extends Place {

	private String tokenName;

	public DataGridPlace(String token) {
		this.tokenName = token;
	}

	public String getTokenName() {
		return tokenName;
	}

	public static class Tokenizer implements PlaceTokenizer<DataGridPlace> {

		@Override
		public String getToken(DataGridPlace place) {
			return place.getTokenName();
		}

		@Override
		public DataGridPlace getPlace(String token) {
			return new DataGridPlace(token);
		}
	}

}
