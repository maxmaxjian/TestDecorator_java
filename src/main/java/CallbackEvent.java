import java.util.LinkedHashSet;
import java.util.Set;

public class CallbackEvent<TArg> implements EventListener<TArg> {

    private final Set<EventListener<TArg>> mCallbacks = new LinkedHashSet<>();
    private final Set<EventListener<TArg>> mSingleFireCallbacks = new LinkedHashSet<>();

    private TArg mLastExecArg = null;

    public TArg getValue() {
        return mLastExecArg;
    }

    public void register(final EventListener<TArg> callback) {
        if (callback == null) {
            return;
        }
        mCallbacks.add(callback);
    }

    public void registerAndRun(final EventListener<TArg> callback) {
        if (callback == null) {
            return;
        }
        register(callback);
        callback.onEvent(mLastExecArg);
    }

    public void registerSingleFire(final EventListener<TArg> callback) {
        if (callback == null) {
            return;
        }
        mCallbacks.add(callback);
        mSingleFireCallbacks.add(callback);
    }

    public void unregister(final EventListener<TArg> callback) {
        if (callback == null) {
            return;
        }
        mCallbacks.remove(callback);
    }

    public void fireEvent(final TArg arg) {
        mLastExecArg = arg;
        Set<EventListener<TArg>> copy = new LinkedHashSet<>(mCallbacks);
        for (final EventListener<TArg> callback : copy) {
            if (mSingleFireCallbacks.remove(callback)) {
                mCallbacks.remove(callback);
            }
            callback.onEvent(arg);
        }
    }

    public void fireEvent() {
        fireEvent(null);
    }

    public void onEvent(final TArg arg) {
        fireEvent(arg);
    }

    public void clear() {
        mCallbacks.clear();
        mSingleFireCallbacks.clear();
    }

    public int size() {
        return mCallbacks.size();
    }
}
