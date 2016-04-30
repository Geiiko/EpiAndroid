package com.zwertv.epiandroid.requester;

/**
 * Created by Elliott on 28/01/2016.
 */
public interface IRequester {
    public String post(String url, String body) throws RequesterException;
    public String get(String url, String body) throws RequesterException;
}
