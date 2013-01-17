import os, datetime, shutil, sys

installe = raw_input('Install nncloudtv package(y/n): ')

if installe == 'Y':
  os.chdir("../../nncloudtv")
  os.system("mvn clean compile install -DskipTests")
  os.chdir("../nnqueue/installer")

os.chdir("..")
os.system("mvn clean compile")
os.system("mvn clean assembly:assembly -DskipTests")
os.system("mv target/nnqueue-0.0.1-SNAPSHOT-jar-with-dependencies.jar target/nnqueue.jar")


