package com.message.messagequeue.exception.member;

public class MemberFoundException extends RuntimeException {

    public MemberFoundException() {
        super();
    }

    public MemberFoundException(String message) {
        super(message);
    }

    public MemberFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberFoundException(Throwable cause) {
        super(cause);
    }
}
