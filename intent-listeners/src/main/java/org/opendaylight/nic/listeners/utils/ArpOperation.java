/*
 * Copyright (c) 2015 Hewlett-Packard Development Company, L.P. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.nic.listeners.utils;

public enum ArpOperation {

    REQUEST(1), REPLY(2);

    private final int intOperation;

    private ArpOperation(int operationNumber) {
        this.intOperation = operationNumber;
    }

    public int intValue() {
        return intOperation;
    }

    public static ArpOperation loadFromInt(int intOperation) {
        for (ArpOperation operation : ArpOperation.values()) {
            if (operation.intOperation == intOperation) {
                return operation;
            }
        }
        return null;
    }

}
