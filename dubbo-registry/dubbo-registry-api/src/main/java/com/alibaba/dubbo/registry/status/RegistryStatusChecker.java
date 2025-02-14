/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.registry.status;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.status.Status;
import com.alibaba.dubbo.common.status.StatusChecker;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.support.AbstractRegistryFactory;

import java.util.Collection;

/**
 * RegistryStatusChecker
 *
 */
@Activate
public class RegistryStatusChecker implements StatusChecker {

    public Status check() {
        Collection<Registry> regsitries = AbstractRegistryFactory.getRegistries();
        if (regsitries.isEmpty()) {
            return new Status(Status.Level.UNKNOWN);
        }
        Status.Level level = Status.Level.OK;
        StringBuilder buf = new StringBuilder();
        for (Registry registry : regsitries) {
            if (buf.length() > 0) {
                buf.append(",");
            }
            buf.append(registry.getUrl().getAddress());
            if (!registry.isAvailable()) {
                level = Status.Level.ERROR;
                buf.append("(disconnected)");
            } else {
                buf.append("(connected)");
            }
        }
        return new Status(level, buf.toString());
    }


    public static void main(String[] args) {
        int [] arr = {1,13,2,8,9};
        System.out.println(test(arr));
    }

    private static String test(int[] arr) {

        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if(Integer.parseInt(str + arr[i]) > Integer.parseInt(arr[i] + str)){
                str = str + arr[i];
            }else {
                str = arr[i] + str;
            }
        }
        return str;
    }

}