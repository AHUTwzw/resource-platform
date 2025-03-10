package com.resource.core.service;

public interface IStorage {
    String getStorage(String namespace, String bucket);
    String getStorageHistory(String namespace, String bucket);
}
