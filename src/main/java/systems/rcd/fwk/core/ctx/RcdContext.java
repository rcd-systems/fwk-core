package systems.rcd.fwk.core.ctx;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import systems.rcd.fwk.core.format.csv.RcdCsvService;
import systems.rcd.fwk.core.format.csv.impl.RcdSimpleCsvService;
import systems.rcd.fwk.core.format.json.RcdJsonService;
import systems.rcd.fwk.core.format.json.impl.RcdSimpleJsonService;
import systems.rcd.fwk.core.io.file.RcdTextFileService;
import systems.rcd.fwk.core.io.file.impl.RcdNioTextFileService;
import systems.rcd.fwk.core.log.RcdLogService;
import systems.rcd.fwk.core.log.impl.RcdPrintStreamLogService;

public class RcdContext
{
    private static final RcdContext globalContext = new RcdContext();

    private static final ThreadLocal<RcdContext> threadLocalContext = new ThreadLocal<RcdContext>()
    {
        @Override
        protected RcdContext initialValue()
        {
            return new RcdContext();
        }
    };

    static
    {
        setGlobalServiceSupplier( RcdLogService.class, () -> new RcdPrintStreamLogService() );
        setGlobalServiceSupplier( RcdTextFileService.class, () -> new RcdNioTextFileService() );
        setGlobalServiceSupplier( RcdJsonService.class, () -> new RcdSimpleJsonService() );
        setGlobalServiceSupplier( RcdCsvService.class, () -> new RcdSimpleCsvService() );
    }

    private final Map<Class<? extends RcdService>, RcdService> serviceMap = new HashMap<>();

    private final Map<Class<? extends RcdService>, Supplier<? extends RcdService>> serviceSupplierMap = new HashMap<>();

    private RcdContext()
    {
    }

    @SuppressWarnings("unchecked")
    public static <T extends RcdService> T getService( final Class<T> serviceClass )
    {
        final T service = (T) threadLocalContext.get().
            serviceMap.
            computeIfAbsent( serviceClass, key -> {
                final Supplier<T> supplier = (Supplier<T>) threadLocalContext.get().serviceSupplierMap.get( key );
                return supplier == null ? null : supplier.get();
            } );

        if ( service == null )
        {
            synchronized ( globalContext )
            {
                return (T) globalContext.serviceMap.computeIfAbsent( serviceClass, key -> {
                    final Supplier<T> supplier = (Supplier<T>) globalContext.serviceSupplierMap.get( key );
                    return supplier == null ? null : supplier.get();
                } );
            }
        }
        return service;
    }

    public static <T extends RcdService> void setLocalServiceSupplier( final Class<T> serviceClass, final Supplier<T> serviceSupplier )
    {
        threadLocalContext.get().serviceSupplierMap.put( serviceClass, serviceSupplier );
    }

    public static <T extends RcdService> void setGlobalServiceSupplier( final Class<T> serviceClass, final Supplier<T> serviceSupplier )
    {
        synchronized ( globalContext )
        {
            globalContext.serviceSupplierMap.put( serviceClass, serviceSupplier );
        }
    }

    public <T extends RcdService> void removeService( final Class<T> serviceClass )
    {
        if ( threadLocalContext.get().serviceSupplierMap.remove( serviceClass ) != null )
        {
            threadLocalContext.get().serviceMap.remove( serviceClass );
        }

        synchronized ( globalContext )
        {
            if ( globalContext.serviceSupplierMap.remove( serviceClass ) != null )
            {
                globalContext.serviceMap.remove( serviceClass );
            }
        }
    }
}
