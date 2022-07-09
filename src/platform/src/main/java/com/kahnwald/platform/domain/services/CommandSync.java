package com.kahnwald.platform.domain.services;

import com.kahnwald.platform.data.dto.PlatformReadDTO;

import javax.lang.model.type.NullType;
import java.util.concurrent.CompletionStage;

public interface CommandSync {

    void sendPlatformToCommand(PlatformReadDTO platform);
}
