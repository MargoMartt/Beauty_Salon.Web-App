const triggers = document.querySelectorAll("[data-modaltrigger]")

triggers.forEach((element) => {
  const modalID = element.dataset.modaltrigger
  const modal = document.querySelector(`[data-modal="${modalID}"]`)

  element.addEventListener('click', () => {
    modal.classList.add('modal_opened')
  })
})
