package com.learn.concurrency.patterns.thread_safe.lock_split;

import com.learn.concurrency.patterns.ThreadSafe;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhuwh
 * @date 2018/7/20 14:18
 * @desc
 */
@ThreadSafe
public class OSToolConfiguration {

    private List<String> excutorCommands;

    private String toolName;

    private String user;

    private String password;

    private Lock lockExecutorCommands = new ReentrantLock();

    private Lock lockTooName = new ReentrantLock();
    private Lock lockUser = new ReentrantLock();
    private Lock lockPassword = new ReentrantLock();

    public List<String> getExecutorCommands() {
        lockExecutorCommands.lock();
        try {
            return excutorCommands;
        } finally {
            lockExecutorCommands.unlock();
        }
    }

    public void addExecutorCommands(String executorCommand) {
        lockExecutorCommands.lock();
        try {
            this.excutorCommands.add(executorCommand);
        } finally {
            lockExecutorCommands.unlock();
        }
    }

    public void removeExecutorCommands(String executorCommand) {
        lockExecutorCommands.lock();
        try {
            this.excutorCommands.remove(executorCommand);
        } finally {
            lockExecutorCommands.unlock();
        }
    }

    public String getToolName() {
        lockTooName.lock();
        try {
            return toolName;
        }finally {
            lockTooName.unlock();
        }
    }

    public void setToolName(String toolName) {
        lockTooName.lock();
        try {
            this.toolName = toolName;
        }finally {
            lockTooName.unlock();
        }
    }

    public String getUser() {
        lockUser.lock();
        try {
            return user;
        }finally {
            lockUser.unlock();
        }
    }

    public void setUser(String user) {
        lockUser.lock();
        try {
            this.user = user;
        }finally {
            lockUser.unlock();
        }
    }

    public String getPassword() {
        lockPassword.lock();
        try {
            return password;
        }finally {
            lockPassword.unlock();
        }
    }

    public void setPassword(String password) {
        lockPassword.lock();
        try {
            this.password = password;
        }finally {
            lockPassword.unlock();
        }
    }
}
