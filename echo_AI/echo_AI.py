import os
import json
import sys
from langchain_groq import ChatGroq
from pathlib import Path

'''
Dear Programmers,

When I wrote this code in mid January 2026, I had studied LLM and LangChain concepts recently.
I made that thing in a hurry. The function ask_Echo_AI works fine, but if some Python master
can tell me how to optimize the function get_base_path and others more effectively so that it can take 
less time generating a response from this .exe (current time: minimum 3 seconds, goal is to optimize it to near 1.5 seconds)
God bless you
'''

def get_base_path(): # thats an extra method of no use
    if getattr(sys, 'frozen', False):
        return Path(sys._MEIPASS)
    return Path(__file__).parent

BASE = get_base_path()

def get_config(key):
    value = os.getenv(key)
    if value is None:
        raise Exception(f"Missing required config: {key}")
    return value


def ask_Echo_AI (query):
    try:
        GROQ_API = get_config("GROQ_API_KEY")
        
        if getattr(sys, 'frozen', False):
            EXE_DIR = Path(sys.executable).parent.parent.parent
        else:
            EXE_DIR = Path(__file__).parent.parent.parent

        FOLDER_PATH = EXE_DIR / "data" / "Storage" / "context.json"
        
        try:
            with open(FOLDER_PATH, "r") as file:
                data = json.load(file)
        except FileNotFoundError:
            return "Json not found"

        llm = ChatGroq(model="llama-3.3-70b-versatile", temperature = 0.1, max_tokens = None, reasoning_format = None, timeout = 15, max_retries = 2)

        prompt = f"""
        SYSTEM CONTEXT:
        {data}
        \n USER QUESTION: {query}
        """
        responce = llm.invoke(prompt)
        return responce.content

    except Exception as e:
        print(e)
        return "error"

if __name__ == "__main__":
    import sys
    query = sys.argv[1]
    result = ask_Echo_AI(query)
    print(result)