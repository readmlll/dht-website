/**
 *
 * @param currentPage 当前页
 * @param totalPage   总页数
 * @param showNum     导航最多显示几页
 * 1 10 5
 */
function pageNumNavGenerate(currentPage, totalPage, showNum) {
  let list = []
  let itNum = Math.floor(showNum / 2);
  let left = []
  let right = []

  if (totalPage===undefined||totalPage===0){
      list.push(1)
    return list
  }

  for (let i = itNum, j = 1; i > 0; i--, j++) {
    left.push(currentPage - i)
    right.push(currentPage + j)
  }

  left.filter(num => num > 0).forEach(function (x) {
    list.push(x)
  })

  list.push(currentPage)

  right.filter(num => num < totalPage).forEach(function (x) {
    list.push(x)
  })

  if (list.length < showNum && list[list.length - 1] < totalPage) {
    //右边有更多的可以补
    for (let i = list[list.length - 1] + 1; i <= totalPage && list.length < showNum; i++) {
      list.push(i)
    }

  }

  if (list.length < showNum) {
    //左边有更多的可以补
    let newlist = []
    for (let i = list[0] - 1; i > 0 && newlist.length + list.length < showNum; i--) {
      newlist.push(i)
    }
    newlist.reverse()
    list = newlist.concat(list)
  }

  if (list.length > showNum) {
    list.pop()
  }

  if (list[0] != 1) {
    list.unshift('<')
    list.unshift('首页')
  }

  if (list[list.length - 1] != totalPage) {
    list.push('>')
    list.push('尾页')
  }

  return list
}

function tip() {
  window.layer.load(1, {
    shade: [0.5,'#eee'] //0.1透明度的白色背景
    ,
    content: '<p style="position: relative;left: -39px;">请稍后 , 加载中...</p>',
    success: function (layero) {
      layero.find('.layui-layer-content').css({
        'padding-top': '45px',
        'width': '200px',
        'height': '100px',
        'color':'black',
        'font-size':'16px',
        'font-weight':'900'
      });
    }
  });
}

function closeTip() {
  window.layer.closeAll()
}
export default {
  pageNumNavGenerate,
  tip,
  closeTip
}
