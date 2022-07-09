package com.kahnwald.command.data.clients;

import com.kahnwald.command.data.dto.PlatformReceiveDTO;

public interface PlatformEventProcessor {

    void addPlatform(PlatformReceiveDTO dto);
//    void addPlatform(String dto);
}
