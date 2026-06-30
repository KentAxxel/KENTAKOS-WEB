import os
import glob

directory = 'd:/KENTAKOS-WEB/frontend/src'

for filepath in glob.glob(directory + '/**/*.tsx', recursive=True) + glob.glob(directory + '/**/*.ts', recursive=True):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # We replace any variation we might have put back to the HTTPS version
    new_content = content.replace('http://shop.spring.informaticapp.com:2920', 'https://shop.spring.informaticapp.com')
    new_content = new_content.replace('http://localhost:2920', 'https://shop.spring.informaticapp.com')
    
    if content != new_content:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(new_content)
        print(f"Updated {filepath}")
