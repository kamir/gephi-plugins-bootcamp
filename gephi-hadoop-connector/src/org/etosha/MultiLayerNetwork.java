/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.etosha;

/**
 *
 * @author kamir
 */
public class MultiLayerNetwork {

    static NetworkLayer defaultLayer = null;
    
    public static void initNetworks() {
        defaultLayer = new NetworkLayer();
    }
    
    public static void setDefaultLayer( NetworkLayer nl ) {
        defaultLayer = nl;
    };
    
    public static NetworkLayer getSelected() {
        if ( defaultLayer == null ) initNetworks();
        return defaultLayer;
    }
    
}
