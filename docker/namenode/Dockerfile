
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
FROM openjdk:8u332-jdk
MAINTAINER Arctic
USER root

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

ARG HADOOP_VERSION=2.10.2
ARG APACHE_ARCHIVE=https://archive.apache.org/dist
ARG DEBIAN_MIRROR=http://deb.debian.org

ENV HADOOP_VERSION=${HADOOP_VERSION}
ENV HADOOP_URL=${APACHE_ARCHIVE}/hadoop/common/hadoop-${HADOOP_VERSION}/hadoop-${HADOOP_VERSION}.tar.gz
ENV HADOOP_WEBHDFS_PORT=50070


RUN sed -i "s#http://deb.debian.org#${DEBIAN_MIRROR}#g" /etc/apt/sources.list

RUN set -x \
    && DEBIAN_FRONTEND=noninteractive apt-get -yq update && apt-get -yq install curl wget netcat procps dos2unix net-tools \
    && apt-get clean

RUN set -x \
    && echo "Fetch URL2 is : ${HADOOP_URL}" \
    && curl -fSL "${HADOOP_URL}" -o /tmp/hadoop.tar.gz \
    && mkdir -p /opt/hadoop-$HADOOP_VERSION/logs \
    && tar -xvf /tmp/hadoop.tar.gz -C /opt/ \
    && rm /tmp/hadoop.tar.gz* \
    && mkdir /etc/hadoop \
    && cp /opt/hadoop-$HADOOP_VERSION/etc/hadoop/mapred-site.xml.template /opt/hadoop-$HADOOP_VERSION/etc/hadoop/mapred-site.xml \
    && mkdir /hadoop-data

ENV HADOOP_PREFIX=/opt/hadoop-$HADOOP_VERSION
ENV HADOOP_CONF_DIR=/etc/hadoop
ENV MULTIHOMED_NETWORK=1
ENV HADOOP_HOME=${HADOOP_PREFIX}
ENV HADOOP_INSTALL=${HADOOP_HOME}
ENV USER=root
ENV PATH /usr/bin:/bin:$HADOOP_PREFIX/bin/:$PATH

# Exposing a union of ports across hadoop versions
# Well known ports including ssh
EXPOSE 0-1024 4040 7000-10100 5000-5100 50000-50200 58188 58088 58042

ADD entrypoint.sh /entrypoint.sh
ADD export_container_ip.sh /usr/bin/
RUN chmod a+x /usr/bin/export_container_ip.sh \
    && chmod a+x /entrypoint.sh \
    && dos2unix /usr/bin/export_container_ip.sh \
    && dos2unix /entrypoint.sh


ENV HDFS_CONF_dfs_namenode_name_dir=file:///hadoop/dfs/name
RUN mkdir -p /hadoop/dfs/name
VOLUME /hadoop/dfs/name

ADD run_nn.sh /run_nn.sh
RUN chmod a+x /run_nn.sh && dos2unix /run_nn.sh

CMD ["/bin/bash", "/entrypoint.sh"]
