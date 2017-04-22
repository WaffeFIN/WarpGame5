/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.util;

/**
 * 
 * @author Walter
 */
public class Pair<X, Y> {

	public X x;
	public Y y;

	public Pair() {
	}

	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	public void set(X x, Y y) {
		this.x = x;
		this.y = y;
	}
}
