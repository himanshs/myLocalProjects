package com.exceptions;

public interface IMessages {
	
	/**
	 * Returns the localized message for the given key.
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public String getMessage(String key, String... params);

}
