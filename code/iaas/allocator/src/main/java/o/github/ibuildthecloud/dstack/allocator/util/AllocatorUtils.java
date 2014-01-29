package o.github.ibuildthecloud.dstack.allocator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.ibuildthecloud.dstack.core.constants.CommonStatesConstants;

public class AllocatorUtils {

    private static final Logger log = LoggerFactory.getLogger(AllocatorUtils.class);

    public static Boolean checkAllocateState(long resourceId, String state, String logType) {
        if ( CommonStatesConstants.ACTIVE.equals(state) ) {
            log.info("{} [{}] is already allocated", logType, resourceId);
            return true;
        } else if ( ! CommonStatesConstants.ACTIVATING.equals(state) ) {
            log.error("Can not allocate {} [{}] in allocation state [{}]", logType, resourceId, state);
            return false;
        }

        return null;
    }

    public static Boolean checkDeallocateState(long resourceId, String state, String logType) {
        if ( CommonStatesConstants.INACTIVE.equals(state) ) {
            log.info("{} [{}] is already deallocated", logType, resourceId);
            return true;
        }

        if ( ! CommonStatesConstants.DEACTIVATING.equals(state) ) {
            log.info("Can not deallocate {} [{}], is not in an deactivating allocation state", logType, resourceId);
            return false;
        }

        return null;
    }

}