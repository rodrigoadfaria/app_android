package com.vinacredit.activity.Sale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeadSetUtils {
	private static final String HEADSET_STATE_PATH = "/sys/class/switch/h2w/state";
	private static final String HEADSET_NAME_PATH = "/sys/class/switch/h2w/name";
	private static final int BIT_HEADSET = (1 << 0);
	private static final int BIT_HEADSET_NO_MIC = (1 << 1);
	private static final int SUPPORTED_HEADSETS = (BIT_HEADSET | BIT_HEADSET_NO_MIC);
	private static final int HEADSETS_WITH_MIC = BIT_HEADSET;

	static boolean checkHeadset() {
		String name = getHeadsetName();
		if (name != null && (0 != name.length())) {
			//if (false == name.trim().equalsIgnoreCase("headset"))
			//	return false;
			String state = getHeadsetState();
			if (state == null || 0 == state.length())
				return false;
			int i = Integer.parseInt(state.trim());
			int headsetState = i & SUPPORTED_HEADSETS;
			return ((headsetState & HEADSETS_WITH_MIC) != 0);
		}
		return false;
	}

	private static String getHeadsetName() {
		return readLineEx(HEADSET_NAME_PATH);
	}

	private static String getHeadsetState() {
		return readLineEx(HEADSET_STATE_PATH);
	}

	private static String readLineEx(String paramString) {
		File localFile = new File(paramString);
		if (false == localFile.exists())
			return null;

		try {
			String localFilePath = localFile.getAbsolutePath();
			FileInputStream localFileInputStream = new FileInputStream(
					localFilePath);
			InputStreamReader localInputStreamReader = new InputStreamReader(
					localFileInputStream);
			BufferedReader localBufferedReader = new BufferedReader(
					localInputStreamReader);
			String str = localBufferedReader.readLine();
			localBufferedReader.close();
			localInputStreamReader.close();
			localFileInputStream.close();
			return str;
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
			return null;
		}

	}
}