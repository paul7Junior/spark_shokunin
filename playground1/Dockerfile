FROM debian:latest
# https://mungingdata.com/java/sdkman-multiple-versions-java-sbt-scala/

SHELL ["/bin/bash", "-c"]

RUN apt-get update && apt-get upgrade -y
RUN apt-get install -y vim curl unzip zip

# install sdkman
RUN curl -s "https://get.sdkman.io" | bash
# RUN chmod a+x "$HOME/.sdkman/bin/sdkman-init.sh"

RUN source "$HOME/.sdkman/bin/sdkman-init.sh" \
    && sdk install java 8.0.292.hs-adpt \
    && sdk install sbt 1.3.13 \
    && sdk install scala 2.11.12 \
    && sdk install spark 2.4.7

# Once the real binaries exist these are 
# the symlinked paths that need to exist on PATH
ENV PATH=/root/.sdkman/candidates/java/current/bin:$PATH
ENV PATH=/root/.sdkman/candidates/scala/current/bin:$PATH
ENV PATH=/root/.sdkman/candidates/sbt/current/bin:$PATH
ENV PATH=/root/.sdkman/candidates/spark/current/bin:$PATH

RUN sh -c '(echo "#!/usr/bin/env sh" && curl -L https://github.com/com-lihaoyi/Ammonite/releases/download/2.5.0/2.13-2.5.0) > /usr/local/bin/amm && chmod +x /usr/local/bin/amm'

# install python for jupyter notebooks

RUN sudo apt-get install -y python3
RUN apt-get -y install python3-pip
RUN pip3 install --upgrade pip
RUN pip3 install jupyter
RUN pip install spylon-kernel


# install R
RUN apt-get -y install r-base
RUN apt-get -y install libxml2-dev
RUN apt-get -y install libssl-dev
RUN apt-get -y install libcurl4-openssl-dev
RUN apt-get -y install pandoc

---RUN R -S 'install.packages("languageserver")'
---RUN R -S 'install.packages("rmarkdown")'
---RUN R -S 'install.packages("rscala")'

# add .Rprofile to ~


