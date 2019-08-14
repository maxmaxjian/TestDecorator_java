public class TimerBasedExpirePolicy implements ExpirePolicy {

    private final CallbackEvent<Boolean> mWallpaperExpiredEvent;

    private final ExpirationTimer mTimer;

    public TimerBasedExpirePolicy() {
        mTimer = new ExpirationTimer();
        mWallpaperExpiredEvent = new CallbackEvent<>();
        mTimer.getTimerExpiredEvent().register(new EventListener<Void>() {
            @Override
            public void onEvent(final Void aVoid) {
                expire();
            }
        });
    }

    @Override
    public CallbackEvent<Boolean> getWallpaperExpiredEvent() {
        return mWallpaperExpiredEvent;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void destroy() {
        mTimer.stopTimer();

    }
}
