#!/usr/bin/env python

from __future__ import unicode_literals

import requests
import sys

#sql = "select * from helix_mxyc limit 1"
sql = sys.argv[1]
print sql

resp = requests.get("http://192.168.1.102:9201/_sql?sql=%s" % sql)

print resp.text
