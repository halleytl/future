#!/usr/bin/env python
#-*- coding:utf-8 -*-

from optparse import OptionParser
import sys

if __name__ == "__main__":
    while True:
        line = sys.stdin.readline()
        if not line:
            break
        print line.strip() + "\tXXX"
    pass
    usage = "usage: %prog [options] arg1 arg2"
    parser = OptionParser(usage=usage)
    parser.add_option("-v", "--verbose",
                      action="store_true", dest="verbose", default=True,
                      help="make lots of noise [default]")
    parser.add_option("-q", "--quiet",
                      action="store_false", dest="verbose",
                      help="be vewwy quiet (I'm hunting wabbits)")
    parser.add_option("-f", "--filename",
                      metavar="FILE", help="write output to FILE"),
    parser.add_option("-m", "--mode",
                      default="intermediate",
                  help="interaction mode: novice, intermediate, "
                       "or expert [default: %default]")
