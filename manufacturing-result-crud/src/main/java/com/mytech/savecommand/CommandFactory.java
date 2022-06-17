package com.mytech.savecommand;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
@Component
public class CommandFactory {
    @Autowired
    SaveSimpleResultCommand simpleResultCommand;
    @Autowired
    SaveComplexResultCommand complexResultCommand;

    public IManufacturingResultSaveCommand getCommand(String commandStr){
        Preconditions.checkNotNull(commandStr,"command String is null, CommandFactory can not create command object");
        if ("simpleresult".toLowerCase().equals(commandStr.toLowerCase())) return simpleResultCommand;
        if ("complexResult".toLowerCase().equals(commandStr.toLowerCase())) return complexResultCommand;
        return null;
    }
}
