#  Licensed to the Apache Software Foundation (ASF) under one
#  or more contributor license agreements.  See the NOTICE file
#  distributed with this work for additional information
#  regarding copyright ownership.  The ASF licenses this file
#  to you under the Apache License, Version 2.0 (the
#  "License"); you may not use this file except in compliance
#  with the License.  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
# limitations under the License.

ARG SPARK_VERSION=3.3.3

FROM apache/spark:${SPARK_VERSION}

ARG MAVEN_MIRROR=https://repo.maven.apache.org/maven2
ARG OPTIMIZER_JOB=optimizer-job.jar
USER root

RUN apt-get update \
    && apt-get install -y wget

RUN cd $SPARK_HOME/jars \
    && wget ${MAVEN_MIRROR}/software/amazon/awssdk/bundle/2.20.5/bundle-2.20.5.jar \
    && wget ${MAVEN_MIRROR}/software/amazon/awssdk/url-connection-client/2.20.5/url-connection-client-2.20.5.jar \
    && chown spark:spark *.jar \
    && mkdir -p $SPARK_HOME/usrlib

COPY $OPTIMIZER_JOB $SPARK_HOME/usrlib/optimizer-job.jar
