package com.gwt2go.dev.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class Dnd2Place extends Place {

	private String dndName;

	public Dnd2Place(String token) {
		this.dndName = token;
	}

	public String getDndName() {
		return dndName;
	}

	public static class Tokenizer implements PlaceTokenizer<Dnd2Place> {
		@Override
		public String getToken(Dnd2Place place) {
			return place.getDndName();
		}

		@Override
		public Dnd2Place getPlace(String token) {
			return new Dnd2Place(token);
		}
	}
}
