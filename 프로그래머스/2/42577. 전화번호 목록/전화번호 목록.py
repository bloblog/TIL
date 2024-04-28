def solution(phone_book):
    answer = True
    dic = dict()
    for i in phone_book:
        for j in range(1, len(i)):
            dic[i[:j]] = 3
    # head = list(dic.keys())
    
    for i in phone_book:
        if i in dic:
            answer = False
            break
        else:
            answer = True
        
    return answer