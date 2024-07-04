function solution(cacheSize, cities) {
    var answer = 0;
    var cache = [];
    
    if (cacheSize == 0) {
        return cities.length * 5;
    }
    
    for (let city of cities) {
        var idx = cache.indexOf(city.toLowerCase());
        if (idx == -1) {
            // miss
            answer += 5;

            if (cache.length >= cacheSize) {
                cache.shift();
            }   
            cache.push(city.toLowerCase());
            

        } else {
            // cache hit 인 경우
            answer += 1;

            cache.splice(idx, 1);
            cache.push(city.toLowerCase());
        }   
    }
    
    return answer;
}