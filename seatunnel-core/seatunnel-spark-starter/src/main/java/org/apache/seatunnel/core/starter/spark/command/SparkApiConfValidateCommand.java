/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.seatunnel.core.starter.spark.command;

import org.apache.seatunnel.core.starter.command.Command;
import org.apache.seatunnel.core.starter.config.ConfigBuilder;
import org.apache.seatunnel.core.starter.exception.ConfigCheckException;
import org.apache.seatunnel.core.starter.spark.args.SparkCommandArgs;
import org.apache.seatunnel.core.starter.spark.config.SparkApiConfigChecker;
import org.apache.seatunnel.core.starter.utils.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

/**
 * Use to validate the configuration of the SeaTunnel API.
 */
public class SparkApiConfValidateCommand implements Command<SparkCommandArgs> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SparkApiConfValidateCommand.class);

    private final SparkCommandArgs sparkCommandArgs;

    public SparkApiConfValidateCommand(SparkCommandArgs sparkCommandArgs) {
        this.sparkCommandArgs = sparkCommandArgs;
    }

    @Override
    public void execute() throws ConfigCheckException {
        Path configPath = FileUtils.getConfigPath(sparkCommandArgs);
        // todo: validate the config by new api
        ConfigBuilder configBuilder = new ConfigBuilder(configPath);
        new SparkApiConfigChecker().checkConfig(configBuilder.getConfig());
        LOGGER.info("config OK !");
    }
}