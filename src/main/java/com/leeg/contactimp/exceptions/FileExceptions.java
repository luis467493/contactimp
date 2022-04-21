package com.leeg.contactimp.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileExceptions {

	public static class FileCannotBeRead extends BaseException {
		private static final long serialVersionUID = 1L;

	public FileCannotBeRead(String msg) {
			super(msg);
		}
	}

}
