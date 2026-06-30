import os
import glob

directory = 'd:/KENTAKOS-WEB/frontend/src'
old_url = 'http://localhost:2920'
new_url = 'https://shop.spring.informaticapp.com'

for filepath in glob.glob(directory + '/**/*.tsx', recursive=True) + glob.glob(directory + '/**/*.ts', recursive=True):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    if old_url in content:
        new_content = content.replace(old_url, new_url)
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(new_content)
        print(f"Replaced in {filepath}")
