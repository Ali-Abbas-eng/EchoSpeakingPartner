import os
from typing import Union


API_KEY_FILE = os.path.join('secret', 'OpenAI', 'API_KEY.txt')
API_KEY = open(API_KEY_FILE).read()

IELTS_PART_1_PROMPT_FILE = os.path.join('secret', 'IELTS', 'PART_1_SYSTEM_PROMPT.txt')
IELTS_PART_1_PROMPT = open(IELTS_PART_1_PROMPT_FILE).read()

IELTS_PART_2_PROMPT_FILE = os.path.join('secret', 'IELTS', 'PART_2_SYSTEM_PROMPT.txt')
IELTS_PART_2_PROMPT = open(IELTS_PART_2_PROMPT_FILE).read()

IELTS_PART_3_PROMPT_FILE = os.path.join('secret', 'IELTS', 'PART_3_SYSTEM_PROMPT.txt')
IELTS_PART_3_PROMPT = open(IELTS_PART_3_PROMPT_FILE).read()

IELTS_PART_1_DUMMY_START_TRIGGER_FILE = os.path.join('secret', 'IELTS', 'DUMMY_SESSION_START_TRIGGER.TXT')
IELTS_PART_1_DUMMY_START_TRIGGER: str = open(IELTS_PART_1_DUMMY_START_TRIGGER_FILE).read()

IELTS_POSSIBLE_TOPICS_LIST_FILE = os.path.join('secret', 'IELTS', 'IELTS_POSSIBLE_TOPICS.json')
IELTS_POSSIBLE_TOPICS_LIST = list(eval(open(IELTS_POSSIBLE_TOPICS_LIST_FILE).read()).values())
