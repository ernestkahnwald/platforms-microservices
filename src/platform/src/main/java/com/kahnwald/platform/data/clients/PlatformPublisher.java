package com.kahnwald.platform.data.clients;

import com.kahnwald.platform.data.dto.PlatformPublishedDTO;

public interface PlatformPublisher {
    void publishNewPlatform(PlatformPublishedDTO dto);
}
