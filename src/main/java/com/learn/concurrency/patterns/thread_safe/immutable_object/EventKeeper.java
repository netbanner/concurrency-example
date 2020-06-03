package com.learn.concurrency.patterns.thread_safe.immutable_object;

import java.util.UUID;

/**
 * @author zhuwh
 * @date 2018/7/19 16:38
 * @desc
 */
public class EventKeeper {

    private volatile Event lastEvent = new Event(null, null, null, null, null);


    public void acceptEvent(String name, String type, String username, byte[] payload) {
        switch (type) {
            case "STORAGE":
                lastEvent = new Event(UUID.randomUUID().toString(), name, type, username, payload);
                break;
            default:
                break;
        }
    }


    static final class Event {
        private final String id;
        private final String name;
        private final String type;
        private final String username;
        private final byte[] payload;

        public Event(String id, String name, String type, String username, byte[] payload) {
            super();
            this.id = id;
            this.name = name;
            this.type = type;
            this.username = username;
            this.payload = payload;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getUsername() {
            return username;
        }

        public byte[] getPayload() {
            return payload;
        }

    }
}

