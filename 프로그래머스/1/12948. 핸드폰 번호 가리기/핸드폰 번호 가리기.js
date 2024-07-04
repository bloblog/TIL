function solution(phone_number) {
    var answer = '';
    var last_four = phone_number.slice(-4);
    
    answer = '*'.repeat(phone_number.length - 4) + last_four;
    
    return answer;
}