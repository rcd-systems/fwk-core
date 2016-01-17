package systems.rcd.fwk.core.ctx;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import systems.rcd.fwk.core.format.json.RcdJsonService;
import systems.rcd.fwk.core.format.json.impl.RcdSimpleJsonService;
import systems.rcd.fwk.core.io.file.RcdFileService;
import systems.rcd.fwk.core.io.file.impl.RcdNioFileService;
import systems.rcd.fwk.core.log.RcdLogService;
import systems.rcd.fwk.core.log.impl.RcdPrintStreamLogService;

public class RcdContext {
    private static final RcdContext globalContext = new RcdContext(new ConcurrentHashMap<>(), new ConcurrentHashMap<>());
    private static final ThreadLocal<RcdContext> threadLocalContext = new ThreadLocal<RcdContext>() {
        @Override
        protected RcdContext initialValue() {
            return new RcdContext(new HashMap<>(), new HashMap<>());
        };
    };
    static {
        setGlobalServiceSupplier(RcdLogService.class, () -> new RcdPrintStreamLogService());
        setGlobalServiceSupplier(RcdFileService.class, () -> new RcdNioFileService());
        setGlobalServiceSupplier(RcdJsonService.class, () -> new RcdSimpleJsonService());
    }

    private final Map<Class<? extends RcdService>, RcdService> serviceMap;
    private final Map<Class<? extends RcdService>, Supplier<? extends RcdService>> serviceSupplierMap;

    private RcdContext(final Map<Class<? extends RcdService>, RcdService> serviceMap,
            final Map<Class<? extends RcdService>, Supplier<? extends RcdService>> serviceSupplierMap) {
        this.serviceMap = serviceMap;
        this.serviceSupplierMap = serviceSupplierMap;
    }

    @SuppressWarnings("unchecked")
    public static <T extends RcdService> T getService(final Class<T> serviceClass) {

        final T service = (T) threadLocalContext.get().serviceMap.computeIfAbsent(serviceClass, key -> {
            final Supplier<T> supplier = (Supplier<T>) threadLocalContext.get().serviceSupplierMap.get(key);
            return supplier == null ? null : supplier.get();
        });
        if (service == null) {
            return (T) globalContext.serviceMap.computeIfAbsent(serviceClass, key -> {
                final Supplier<T> supplier = (Supplier<T>) globalContext.serviceSupplierMap.get(key);
                return supplier == null ? null : supplier.get();
            });
        }
        return service;
    }

    public static <T extends RcdService> void setLocalServiceSupplier(final Class<T> serviceClass,
            final Supplier<T> serviceSupplier) {
        threadLocalContext.get().serviceSupplierMap.put(serviceClass, serviceSupplier);
    }

    public static <T extends RcdService> void setGlobalServiceSupplier(final Class<T> serviceClass,
            final Supplier<T> serviceSupplier) {
        globalContext.serviceSupplierMap.put(serviceClass, serviceSupplier);
    }

    public <T extends RcdService> void removeService(final Class<T> serviceClass) {
        if (threadLocalContext.get().serviceSupplierMap.remove(serviceClass) != null) {
            threadLocalContext.get().serviceMap.remove(serviceClass);
        }

        if (globalContext.serviceSupplierMap.remove(serviceClass) != null) {
            globalContext.serviceMap.remove(serviceClass);
        }
    }
}
