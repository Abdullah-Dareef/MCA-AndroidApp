package com.academy.accounts;

import com.google.android.gms.auth.GoogleAuthUtil;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


public class AccountHolder {
	private static String accountName = null;
	
	private static final String KEY_ACCOUNT_NAME = "account_name";
	
	public static Account getGoogleAccountByName(Context c, String name){
		if(name != null)
		{
			AccountManager manager = AccountManager.get(c);
			Account[] accounts = manager.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
			for(Account account:accounts)
			{
				if(account.name.equals(name))
					return account;
			}
			
		}
		return null;
			
	}

	public static String getAccountName(Context c){
		if(accountName != null)
			return accountName;
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
		return prefs.getString(KEY_ACCOUNT_NAME, null);
		
	}
	
	public static void setAccountName(Context c, String name){
		Editor editor = PreferenceManager.getDefaultSharedPreferences(c).edit();
		editor.putString(KEY_ACCOUNT_NAME, name);
		editor.apply();
		
		accountName = name;
	}
	
	public static void removeAccount(Context c){
		Editor editor = PreferenceManager.getDefaultSharedPreferences(c).edit();
		editor.remove(KEY_ACCOUNT_NAME);
		editor.apply();
		
		accountName = null;
	}
	
	
}
