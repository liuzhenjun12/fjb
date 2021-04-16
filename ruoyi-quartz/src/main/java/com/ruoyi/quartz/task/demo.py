def getData(url):
    head={
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36"
    }
    request=urllib2.Request(url,headers=head)
    html=""
    try:
        response=urllib2.urlopen(request)
        html=response.read().decode('utf-8')
        pat = '"pure_title": "(.*?)"'
        keyword = re.findall(pat, html, re.S)
        for hot_word in keyword:
            print(hot_word)
            i = quote_plus(str(hot_word))
            link = 'https://www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd={i}&rsv_idx=2&rsv_dl=fyb_n_homepage&hisfilter=1'.format(i=i)
            print(link)
            
    except urllib2.URLError as e:
        if hasattr(e,"code"):
            print(e.code)
        if hasattr(e,"reason"):
            print(e.reason)
    return html
