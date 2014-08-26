/*
 * Copyright (c) 2013 DataTorrent, Inc. ALL Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datatorrent.contrib.hbase;

import com.datatorrent.lib.io.SimpleSinglePortInputOperator;
import com.datatorrent.api.annotation.OutputPortFieldAnnotation;
import com.datatorrent.api.BaseOperator;
import com.datatorrent.api.Context.OperatorContext;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.InputOperator;

/**
 *
 */
public class HBaseRowStringGenerator extends BaseOperator implements InputOperator
{

  int rowCount;

  @OutputPortFieldAnnotation(name = "outputPort")
  public final transient DefaultOutputPort<String> outputPort = new DefaultOutputPort<String>();

  @Override
  public void emitTuples()
  {

    StringBuffer s=new StringBuffer();
    s.append("street"+rowCount+",");
    s.append("city"+rowCount+",");
    s.append("state"+rowCount+",");
    s.append("row"+rowCount);
    ++rowCount;
    if(rowCount==100000)
      rowCount=0;
    outputPort.emit(s.toString());
  }

  @Override
  public void setup(OperatorContext context)
  {
    super.setup(context);
    rowCount = 0;
  }

}