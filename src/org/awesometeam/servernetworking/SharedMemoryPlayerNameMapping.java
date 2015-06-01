/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.servernetworking;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author michal
 */
public class SharedMemoryPlayerNameMapping {
    
    private final Map<Integer, String> nameMap;
    
    private SharedMemoryPlayerNameMapping() {
        nameMap = new HashMap<>();
    }
    
    public static SharedMemoryPlayerNameMapping getInstance() {
        return SharedMemoryPlayerNameMappingHolder.INSTANCE;
    }
    
    private static class SharedMemoryPlayerNameMappingHolder {

        private static final SharedMemoryPlayerNameMapping INSTANCE = new SharedMemoryPlayerNameMapping();
    }
    
    public synchronized String getName(int ID) {
        return nameMap.get(ID);
    }
    
    public synchronized void addName(int ID, String name) {
        nameMap.put(ID, name);
    }
    
    public synchronized void removeByID(int ID) {
        nameMap.remove(ID);
    }
}
