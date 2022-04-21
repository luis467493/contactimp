package com.leeg.contactimp.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenExceptions {

	public static class InvalidToken extends BaseException {
		private static final long serialVersionUID = 1L;

		public InvalidToken(String msg) {
			super(msg);
		}
	}

	public static class UserNotFoundInToken extends BaseException {
		private static final long serialVersionUID = 1L;

		public UserNotFoundInToken(String msg) {
			super(msg);
		}
	}

}
