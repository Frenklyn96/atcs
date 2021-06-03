import json 
from random import uniform, choice
data = []

labels = [2,3,4,5,6,8,16,18,20,21,32,33,33,34,36,37,38,39,40,41,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,70,71,72,74,76,77,78]

for i in range(30):
    data.append({"id":choice(labels), "time":uniform(0, 100)})

print(data)

with open('data/data.json', 'w', encoding='utf-8') as f:
    json.dump(data, f, ensure_ascii=False, indent=4)