package com.leeg.contactimp.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserExceptions {

	public static class UserNotFound extends BaseException {
		private static final long serialVersionUID = 1L;

		public UserNotFound(String msg) {
			super(msg);
		}
	}

}
