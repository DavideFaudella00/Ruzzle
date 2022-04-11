package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	public List<Pos> trovaParola(String parola, Board board) {
		for (Pos p : board.getPositions()) {
			if (parola.charAt(0) == (board.getCellValueProperty(p).get().charAt(0))) {
				List<Pos> parziale = new ArrayList<Pos>();
				parziale.add(p);
				if (cerca(parola, parziale, 1, board)) {
					if (parziale.size() == 1) {
						return null;
					}
					return parziale;
				}
			}
		}
		return null;
	}

	public boolean cerca(String parola, List<Pos> percorso, int livello, Board board) {
		if (livello == parola.length()) {
			return true;
		}
		Pos ultima = percorso.get(percorso.size() - 1);
		List<Pos> adiancenti = board.getAdjacencies(ultima);
		for (Pos a : adiancenti) {
			if (!percorso.contains(a) && (parola.charAt(livello) == board.getCellValueProperty(a).get().charAt(0))) {
				percorso.add(a);
				if (cerca(parola, percorso, livello + 1, board)) {
					return true;
				}
				percorso.remove(percorso.size() - 1);
			}
		}
		return false;
	}
}
